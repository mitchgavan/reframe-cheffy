(ns app.nav.views.public
  (:require [re-frame.core :as rf]
            ["@chakra-ui/react" :refer [Flex Box]]))

(defn public
  []
  (let [active-nav @(rf/subscribe [:active-nav])
        nav-items [{:id :recipes
                    :name "Recipes"
                    :href "#recipes"
                    :dispatch #(rf/dispatch [:set-active-nav :recipes])}
                   {:id :become-a-chef
                    :name "Chef"
                    :href "#become-a-chef"
                    :dispatch #(rf/dispatch [:set-active-nav :become-a-chef])}
                   {:id :sign-up
                    :name "Sign up"
                    :href "#sign-up"
                    :dispatch #(rf/dispatch [:set-active-nav :sign-up])}
                   {:id :log-in
                    :name "Log in"
                    :href "#log-in"
                    :dispatch #(rf/dispatch [:set-active-nav :log-in])}]]
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