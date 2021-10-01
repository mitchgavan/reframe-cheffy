(ns app.auth.views.profile
  (:require [app.components.page-nav :refer [page-nav]]
            [app.components.form-group :refer [form-group]]
            [reagent.core :as r]
            [re-frame.core :as rf]
            ["@chakra-ui/react" :refer [Container Text Box Flex Button]]))

(defn profile
  []
  (let [{:keys [first-name last-name]} @(rf/subscribe [:active-user-profile])
        initial-values {:first-name first-name :last-name last-name}
        values (r/atom initial-values)]
    (fn []
      [:<>
       [page-nav {:center "Profile"
                  :right [:> Button {:color-scheme "gray"
                                     :on-click #(rf/dispatch [:log-out])}
                          "Log out"]}]
       [:> Container {:max-w "md"}
        [:> Box {:border-radius "sm"
                 :p 3
                 :pt 1}
         [:> Text {:as "h4"
                   :py "10"}
          "Personal Info"]
         [form-group {:id :first-name
                      :label "First name"
                      :type "text"
                      :values values}]
         [form-group {:id :last-name
                      :label "Last name"
                      :type "text"
                      :values values}]
         [:> Flex {:justify-content "flex-end"}
          [:> Button {:color-scheme "green" :on-click #(rf/dispatch [:update-profile @values])}
           "Save"]]]
        [:> Box {:border-radius "sm"
                 :background-color "gray.100"
                 :p 3
                 :pt 1
                 :mt 10}
         [:> Text {:as "h4"
                   :py "10"}
          "Danger Zone"]
         [:> Flex {:justify-content "flex-end"}
          [:> Button {:color-scheme "red"
                      :on-click #(when (js/confirm "This will delete your account")
                                   (rf/dispatch [:delete-account]))}
           "Delete Account"]]]]])))