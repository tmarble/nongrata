(ns nongrata.views.welcome
  (:require [nongrata.views.common :as common]
            [noir.content.getting-started]
            [noir.session :as session])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(defpage welcome "/welcome" []
         (common/layout
           [:p 
            (if (not (session/get "browser-id"))
              [:a {"href" "#" "id" "browserid" "title" "Sign-in with BrowserID"} 
               [:img {"id" "signin-img" "src" "/img/sign_in_blue.png" "alt" "Sign in"}]]
              "You are signed in!")]))
