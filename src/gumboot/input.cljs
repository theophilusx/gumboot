(ns gumboot.input
  (:require [clojure.string :as string]))

(defn input
  ([type id model]
   (input type id model {}))
  ([type id model extra-attributes]
   (let [attr {:type      (name type)
               :id        (name id)
               :on-change (fn [evt]
                            (swap! model assoc
                                   id (.-value (.-target evt))))}]
     [:div.form-group
      [:label {:for (name id)} (string/capitalize (name id))]
      [:input (into
               attr
               (for [k (keys extra-attributes)]
                 [k (k extra-attributes)]))]])))

(defn text
  ([id model]
   (input :text id model))
  ([id model attrs]
   (input :text id model attrs)))

(defn password
  ([id model]
   (input :password id model))
  ([id model attrs]
   (input :password id model attrs)))

(defn tel
  ([id model]
   (input :tel id model))
  ([id model attrs]
   (input :tel id model attrs)))

(defn email
  ([id model]
   (input :email id model))
  ([id model attrs]
   (input :email id model attrs)))

(defn number
  ([id model]
   (input :number id model))
  ([id model attrs]
   (input :number id model attrs)))

(defn number-range
  ([id model min max]
   (input :range id model {:min min :max max}))
  ([id model min max attrs]
   (input :range id model (assoc attrs :min min :max max))))

