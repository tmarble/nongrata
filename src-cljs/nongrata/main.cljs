(ns nongrata.main
  (:require [clojure.browser.event :as event]
            [clojure.browser.dom :as dom]))

(defn log [& args]
  (.log js/console (apply pr-str args)))

(defn log-obj [obj]
  (.log js/console obj))

(js/alert "Hello world!")
