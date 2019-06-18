(ns gumboot.input
  (:require [gumboot.utils :as utils]))

(defn value-of [e]
  (-> e .-target .-value))

(defn mk-options [parent v]
  (into
   parent
   (for [o v]
     [:option {:value (utils/str->keyword o)} o])))

(defn input [type id model extra-attributes]
  (let [attr (merge {:class     "form-control"
                     :type      (name type)
                     :id        id
                     :on-change (fn [evt]
                                  (swap! model assoc id (value-of evt)))}
                    extra-attributes)]
    (if (contains? attr :value)
      (swap! model assoc id (:value attr)))
    [:div.form-group
     [:label.pr-2 {:for id} (utils/keyword->title id)]
     [:input attr]]))

(defn inline-input [type id model extra-attrs label-class]
  (let [attr (merge {:class     "form-control col"
                     :type      (name type)
                     :id        id
                     :on-change (fn [evt]
                                  (swap! model assoc id (value-of evt)))}
                    extra-attrs)]
    (if (contains? attr :value)
      (swap! model assoc id (:value attr)))
    [:div.form-group.row
     [:label {:class label-class
              :for   id} (utils/keyword->title id)]
     [:input attr]]))

(defn make-input [inline type id model attrs label-class]
  (if inline
    (inline-input type id model attrs label-class)
    (input type id model attrs)))

(defn text [id model & {:keys [inline attrs label-class]
                        :or {inline false
                             attrs {}
                             label-class "col-sm-2 col-form-label"}}]
  (make-input inline :text id model attrs label-class))

(defn password [id model & {:keys [inline attrs label-class]
                            :or {inline false
                                 attrs {}
                                 label-class "col-sm-2 col-form-label"}}]
  (make-input inline :password id model attrs label-class))

(defn tel [id model & {:keys [inline attrs label-class]
                       :or {inline false
                            attrs {}
                            label-class "col-sm-2 col-form-label"}}]
  (make-input inline :tel id model attrs label-class))

(defn email [id model & {:keys [inline attrs label-class]
                         :or {inline false
                              attrs {}
                              label-class "col-sm-2 col-form-label"}}]
  (make-input inline :email id model attrs label-class))

(defn number [id model & {:keys [inline attrs label-class]
                          :or {inline false
                               attrs {}
                               label-class "col-sm-2 col-form-label"}}]
  (make-input inline :number id model
              (merge attrs
                     {:on-change
                      (fn [e]
                        (swap! model assoc id
                               (js/parseFloat (value-of e))))})
              label-class))

(defn number-range [id model min max & {:keys [inline attrs label-class]
                                        :or {inline false
                                             attrs {}
                                             label-class "col-sm-2 col-form-label"}}]
  (make-input inline :range id model
               (merge attrs
                      {:min min
                       :max max
                       :on-change (fn [e]
                                    (swap! model assoc id
                                           (js/parseFloat (value-of e))))})
               label-class))

(defn date [id model & {:keys [inline attrs label-class]
                        :or {inline false
                             attrs {}
                             label-class "col-sm-2 col-form-label"}}]
  (make-input inline :date id model attrs label-class))

(defn time-input [id model & {:keys [inline attrs label-class]
                              :or {inline false
                                   attrs {}
                                   label-class "col-sm-2 col-form-label"}}]
  (make-input inline :time id model attrs label-class))

(defn basic-list-input [id choices model extra-attrs default]
  (let [attrs (merge {:class     "form-control"
                      :type      "text"
                      :id        id
                      :name      id
                      :list      (str id "-list")
                      :on-change (fn [el]
                                   (swap! model assoc id (value-of el)))}
                     extra-attrs)]
    [:div.form-group
     [:label.pr-2 {:for id} (utils/keyword->title id)]
     [:input attrs]
     (into
      [:datalist {:id (str id "-list")}]
      (for [c choices]
        (if (= c default)
          [:option {:value c :selected true} (str c)]
          [:option {:value c} (str c)])))]))

(defn inline-list-input [id choices model extra-attrs label-class default]
  (let [attrs (merge {:class "form-control col"
                      :type      "text"
                      :id        id
                      :name      id
                      :list      (str id "-list")
                      :on-change (fn [el]
                                   (swap! model assoc id (value-of el)))}
                     extra-attrs)]
    [:div.form-group.row
     [:label {:class label-class
              :for id} (utils/keyword->title id)]
     [:input attrs]
     (into
      [:datalist {:id (str id "-list")}]
      (for [c choices]
        (if (= c default)
          [:option {:value c :selected true} (str c)]
          [:option {:value c} (str c)])))]))

(defn list-input [id choices model & {:keys [inline attrs label-class default]
                                      :or {inline false
                                           attrs {}
                                           label-class "col-sm-2 col-form-label"
                                           default (first choices)}}]
  (if inline
    (inline-list-input id choices model attrs label-class default)
    (basic-list-input id choices model attrs default)))

(defn basic-select [id options model extra-attrs default]
  (swap! model assoc id default)
  (let [attrs (merge {:class     "form-control"
                      :id        id
                      :on-change (fn [el]
                                   (swap! model assoc id (value-of el)))
                      :defaultValue default}
                     extra-attrs)]
    [:div.form-group
     [:label.control-label {:for (name id)} (utils/keyword->title id)]
     (into
      [:select attrs]
      (for [o options]
        (if (vector? o)
          (let [[v d] o]
            [:option {:value v} (str d)])
          [:option {:value o} (str o)])))]))

(defn inline-select [id options model extra-attrs default label-class]
  (swap! model assoc id default)
  (let [attrs (merge {:class     "form-control col"
                      :id        id
                      :on-change (fn [el]
                                   (swap! model assoc id (value-of el)))
                      :defaultValue default}
                     extra-attrs)]
    [:div.form-group.row
     [:label {:class label-class
              :for   (name id)} (utils/keyword->title id)]
     (into
      [:select attrs]
      (for [o options]
        (if (vector? o)
          (let [[v d] o]
            [:option {:value v} (str d)])
          [:option {:value o} (str o)])))]))

(defn select [id options model & {:keys [inline attrs label-class default]
                                  :or {inline false
                                       attrs {}
                                       label-class "col-sm-2 col-form-label"
                                       default (if (vector? (first options))
                                                 (first (first options))
                                                 (first options))}}]
  (if inline
    (inline-select id options model attrs default label-class)
    (basic-select id options model attrs default)))

(defn yes-or-no [id model & {:keys [inline attrs default label-class]
                             :or {inline false
                                  attrs {}
                                  default false
                                  label-class "col-sm-2 col-form-label"}}]
  (let [attrs (merge {:on-change (fn [e]
                                   (if (or (= true (value-of e))
                                           (= "true" (value-of e)))
                                     (swap! model assoc id true)
                                     (swap! model assoc id false)))}
                     attrs)]
    (if inline
      (inline-select id [[true "Yes"] [false "No"]] model
                     attrs
                     default
                     label-class)
      (basic-select id [[true "Yes"] [false "No"]] model
                    attrs
                    default))))
