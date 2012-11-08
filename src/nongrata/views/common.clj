(ns nongrata.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page :only [include-css include-js html5]]))

(defpartial layout [& content]
            (html5
              [:head
               [:title "nongrata"]
               (include-js "http://code.jquery.com/jquery-1.7.1.min.js")
               (include-js "https://browserid.org/include.js")
               (include-css "/css/reset.css")
               (include-css "/css/persona-buttons.css")]
              [:body
               [:div#wrapper
                content]
               (include-js "/js/main.js")]))
