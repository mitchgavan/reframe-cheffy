(ns app.components.form-group
  (:require ["@chakra-ui/react" :refer [FormControl FormLabel Input]]))

(defn form-group
  [{:keys [id label type values]}]
  [:> FormControl
   [:> FormLabel {:html-for id} label]
   [:> Input {:id :id
              :type type
              :value (id @values)
              :on-change #(swap! values assoc id (.. % -target -value))}]])