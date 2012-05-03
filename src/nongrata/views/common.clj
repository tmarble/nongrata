(ns nongrata.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page :only [include-css include-js html5]]))

(defpartial layout [& content]
            (html5
              [:head
               [:title "nongrata"]
               (include-js "https://browserid.org/include.js")
               (include-js "http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js")
               (include-css "/css/reset.css")]
              [:body
               [:div#wrapper
                content]]))
