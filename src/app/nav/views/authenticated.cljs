(ns app.nav.views.authenticated
  (:require [re-frame.core :as rf]
            ["@chakra-ui/react" :refer [Flex Box]]))

(defn authenticated
  []
  (let [active-nav @(rf/subscribe [:active-nav])
        nav-items [{:id :saved
                    :name "Saved"
                    :href "#saved"
                    :dispatch #(rf/dispatch [:set-active-nav :saved])}
                   {:id :recipes
                    :name "Recipes"
                    :href "#recipes"
                    :dispatch #(rf/dispatch [:set-active-nav :recipes])}
                   {:id :inboxes
                    :name "Inbox"
                    :href "#inbox"
                    :dispatch #(rf/dispatch [:set-active-nav :inboxes])}
                   {:id :become-a-chef
                    :name "Chef"
                    :href "#become-a-chef"
                    :dispatch #(rf/dispatch [:set-active-nav :become-a-chef])}
                   {:id :profile
                    :name "Profile"
                    :href "#profile"
                    :dispatch #(rf/dispatch [:set-active-nav :profile])}]]
    [:> Flex {:justify-content "flex-end"
              :py 1}
     (for [{:keys [id dispatch name href]} nav-items]
       [:> Box {:key id
                :as "a"
                :href href
                :on-click dispatch
                :ml 3
                :pb 1
                :border-bottom (when (= active-nav id) "2px solid #102A43")}
        name])]))