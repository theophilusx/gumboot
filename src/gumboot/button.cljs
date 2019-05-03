(ns gumboot.button)

(defn button
  ([title action]
   [:button.btn {:type "button" :on-click action} title])
  ([title action attrs]
   [:button (assoc attrs :type "button" :on-click action) title]))

(defn small [title action]
  (button title action {:class "btn btn-sm"}))

(defn small-primary [title action]
  (button title action {:class "btn btn-sm btn-primary"}))

(defn small-secondary [title action]
  (button title action {:class "btn btn-sm btn-secondary"}))

