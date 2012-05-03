(ns nongrata.views.welcome
  (:require [nongrata.views.common :as common]
            [noir.content.getting-started]
            [noir.session :as session])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(defpage "/welcome" []
         (common/layout
           [:p 
            (if (not (session/get "browser-id"))
              [:a {"href" "#"} "sign in"]
              "You are signed in!")]))
