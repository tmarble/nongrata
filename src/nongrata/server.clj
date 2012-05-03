(ns nongrata.server
  (:require [noir.server :as server]))

(server/load-views "src/nongrata/views/")

(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer. (get (System/getenv) "PORT" "11300"))]
    (server/start port {:mode mode
                        :ns 'nongrata})))

