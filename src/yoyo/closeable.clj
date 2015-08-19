(ns yoyo.closeable
  (:require [clojure.tools.logging :as log]))

(defn with-closeable
  [label constructor-fn]
  (fn [opts f]
    (log/infof "Starting %s with opts: %s..." label (prn-str opts))
    (let [obj (constructor-fn opts)]
      (log/infof "Started %s." label)
      (try
        (f obj)
        (finally
          (log/infof "Stopping %s..." label)
          (.close obj)
          (log/infof "Stopped %s." label))))))
