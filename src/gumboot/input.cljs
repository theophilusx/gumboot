(ns gumboot.input
  (:require [clojure.string :as string]
            [gumboot.utils :as utils]))

(defn input
  ([type id model]
   (input type id model {}))
  ([type id model extra-attributes]
   (let [attr (merge {:type      (name type)
                      :id        (name id)
                      :on-change (fn [evt]
                                   (swap! model assoc id (.-value (.-target evt))))}
                     extra-attributes)]
     [:div.form-group
      [:label.pr-2 {:for (name id)} (string/capitalize (name id))]
      [:input attr]])))

(defn text
  ([id model]
   (input :text id model))
  ([id model attrs]
   (input :text id model attrs)))

(defn password
  ([id model]
   (input :password id model {:placeholder "Enter password"}))
  ([id model attrs]
   (input :password id model attrs)))

(defn tel
  ([id model]
   (input :tel id model {:placeholder "Enter phone number"}))
  ([id model attrs]
   (input :tel id model attrs)))

(defn email
  ([id model]
   (input :email id model {:placeholder "Enter email"}))
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

(defn date
  ([id model]
   (input :date id model))
  ([id model attrs]
   (input :date id model attrs)))

(defn time-input
  ([id model]
   (input :time id model))
  ([id model attrs]
   (input :time id model attrs)))

(defn select
  ([id options model]
   (select id options model {}))
  ([id options model extra-attrs]
   (let [attrs {:name id
                :on-change (fn [el]
                             (println (str "id: " id))
                             (println (str "change: " (.-value (.-target el))))
                             (swap! model assoc id (.-value (.-target el))))}]
     [:div.form-group
      [:lable.control-label {:for (name id)} (utils/keyword->title id)]
      (into
       [:select.form-control attrs]
       (for [o options]
         [:option {:value (utils/str->keyword o)} o]))])))
