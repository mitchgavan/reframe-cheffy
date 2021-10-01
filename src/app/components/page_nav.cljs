(ns app.components.page-nav
  (:require ["@chakra-ui/react" :refer [Box Button Flex Heading]]
            ["styled-icons/fa-solid/ChevronLeft" :refer [ChevronLeft]]))

(defn page-nav
  [{:keys [left center right]}]
  [:> Flex {:py 5
            :justify-content "space-between"}
   [:> Box
    (when left [:> Button {:as "a"
                           :my 5
                           :color-scheme "teal"
                           :aria-label "Back"
                           :href left}
                [:> ChevronLeft {:size 16}]])]
   [:> Box
    [:> Heading {:as "h2"
                 :py 5}
     center]]
   [:> Box
    (when right
      right)]])