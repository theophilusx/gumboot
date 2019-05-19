(ns gumboot.table
  (:require [gumboot.utils :as u]))

(defn default-header-fn [headers]
  (into
   [:tr]
   (for [h headers]
     ^{:key (u/defkey h)} [:th h])))

(defn table-header [headers row-fn class]
  [:thead {:class class}
   (row-fn headers)])

(defn default-data-fn [data-rows]
  (into
   [:tbody]
   (for [r data-rows]
     (into
      [:tr]
      (for [c r]
        ^{:key (u/defkey c)} [:td c])))))

(defn table
  [headers data-rows & {:keys [header-fn body-fn table-class header-class]
                        :or {header-fn #'default-header-fn
                             body-fn #'default-data-fn
                             table-class "table table-sm table-striped"
                             header-class "table-warning"}}]
  [:table {:class table-class}
   [table-header headers header-fn header-class] 
   [body-fn data-rows]])

(defn default-v-table-row-fn [rows class]
  (doall (mapv (fn [r]
                 (into
                  [:tr [:th {:class class :scope "row"} (first r)]]
                  (for [d (rest r)]
                    [:td d])))
               rows)))

(defn v-table [data-rows & {:keys [row-fn table-class header-class]
                            :or   {row-fn #'default-v-table-row-fn
                                   table-class "table table-sm"
                                   header-class "table-warning"}}]
  [:table {:class table-class}
   (into
    [:tbody]
    (for [r (row-fn data-rows header-class)]
      r))])

