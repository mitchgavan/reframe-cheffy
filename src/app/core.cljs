(ns app.core
  (:require [reagent.dom :as r]
            [re-frame.core :as rf]
            [app.db]
            ["@chakra-ui/react" :refer [ChakraProvider Grid GridItem Container]]
            ;; -- auth --
            [app.auth.views.profile :refer [profile]]
            [app.auth.views.sign-up :refer [sign-up]]
            [app.auth.views.log-in :refer [log-in]]
            [app.auth.events]
            [app.auth.subs]
            ;; -- inbox --
            [app.inbox.views.inboxes :refer [inboxes]]
            ;; -- become-a-chef --
            [app.become-a-chef.views.become-a-chef :refer [become-a-chef]]
            ;; -- recipes --
            [app.recipes.views.recipes :refer [recipes]]
            ;; -- nav --
            [app.nav.views.nav :refer [nav]]
            [app.nav.events]
            [app.nav.subs]))

(defn pages
  [page-name]
  (case page-name
    :profile [profile]
    :sign-up [sign-up]
    :log-in [log-in]
    :become-a-chef [become-a-chef]
    :inboxes [inboxes]
    :recipes [recipes]
    [recipes]))

(defn app
  []
  (let [active-nav @(rf/subscribe [:active-nav])]
    [:> ChakraProvider
     [:> Container {:max-width "container.lg"}
      [:> Grid
       [:> GridItem
        [nav]]
       [:> GridItem
        [pages active-nav]]]]]))


(defn init
  []
  (rf/dispatch-sync [:initialize-db])
  (r/render [app]
            (.getElementById js/document "app")))
