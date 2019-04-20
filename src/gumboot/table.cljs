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
                             table-class "table"
                             header-class ""}}]
  [:table {:class table-class}
   [table-header headers header-fn header-class] 
   [body-fn data-rows]])



