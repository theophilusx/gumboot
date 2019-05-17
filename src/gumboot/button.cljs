(ns gumboot.button)

(defn button
  ([title action]
   (button title action {}))
  ([title action extra-attrs]
   (let [attrs (merge {:type "button"
                       :class "btn"
                       :on-click action}
                      extra-attrs)]
     [:button attrs title])))

(defn primary [title action]
  (button title action {:class "btn btn-primary"}))

(defn secondary [title action]
  (button title action {:class "btn btn-secondary"}))

(defn small [title action]
  (button title action {:class "btn btn-sm"}))

(defn small-primary [title action]
  (button title action {:class "btn btn-sm btn-primary"}))

(defn small-secondary [title action]
  (button title action {:class "btn btn-sm btn-secondary"}))

