(ns app.auth.views.log-in
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [app.components.page-nav :refer [page-nav]]
            [app.components.form-group :refer [form-group]]
            ["@chakra-ui/react" :refer [Container Flex Button Box]]))

(defn log-in
  []
  (let [initial-values {:email "" :password ""}
        values (r/atom initial-values)]
    (fn []
      [:> Container {:max-w "md"}
       [page-nav {:center "Log in"}]
       [form-group {:id :email
                    :label "Email"
                    :type "email"
                    :values values}]
       [form-group {:id :password
                    :label "Password"
                    :type "password"
                    :values values}]
       [:> Flex {:justify-content "space-between"
                 :mt 4}
        [:> Box {:py 1
                 :px 2}
         [:a {:href "#sign-up"
              :on-click #(rf/dispatch [:set-active-nav :sign-up])}
          "New to Cheffy? Create an account."]]
        [:> Box
         [:> Button {:on-click #(rf/dispatch [:log-in @values])}
          "Log in"]]]])))