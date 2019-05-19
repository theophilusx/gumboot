(ns gumboot.input
  (:require [gumboot.utils :as utils]))

(defn value-of [e]
  (-> e .-target .-value))

(defn mk-options [parent v]
  (into
   parent
   (for [o v]
     [:option {:value (utils/str->keyword o)} o])))

(defn input
  ([type id model]
   (input type id model {}))
  ([type id model extra-attributes]
   (let [attr (merge {:type      (name type)
                      :id        id
                      :on-change (fn [evt]
                                   (swap! model assoc id (value-of evt)))}
                     extra-attributes)]
     [:div.form-group
      [:label.pr-2 {:for id} (utils/keyword->title id)]
      [:input.form-control attr]])))

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

(defn list-input
  ([id choices model]
   (list-input id choices model {}))
  ([id choices model extra-attrs]
   (let [attrs (merge {:type     "text"
                       :id       id
                       :list (str id "-list")
                       :on-click (fn [el]
                                    (swap! model assoc id (value-of el)))}
                      extra-attrs)]
     [:div.form-group
      [:label.pr-2 {:for id} (utils/keyword->title id)]
      [:input.form-control attrs]
      (into
       [:datalist {:id (str id "-list")}]
       (for [c choices]
         [:option (str c)]))])))

(defn select
  ([id options model]
   (select id options model {}))
  ([id options model extra-attrs]
   (if (not (contains? @model id))
     (swap! model assoc id (utils/str->keyword (str (first options)))))
   (let [attrs (merge {:id        id
                       :on-change (fn [el]
                                    (swap! model assoc id (value-of el)))}
                      extra-attrs)]
     [:div.form-group
      [:label.control-label {:for (name id)} (utils/keyword->title id)]
      (into
       [:select.form-control attrs]
       (for [o options]
         [:option {:value (utils/str->keyword (str o))} (str o)]))])))

