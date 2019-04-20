(ns gumboot.typography
  (:require [gumboot.utils :as gu]))

(defn display [size child]
  [(str ":div.display-" size) child])

(defn highlight [data]
  [:mark data])

(defn deleted [data]
  [:del data])

(defn inserted [data]
  [:ins data])

(defn small [data]
  [:small data])

(defn strong [data]
  [:strong data])

(defn underline [data]
  [:u data])

(defn emphasize [data]
  [:em data])

(defn abbrev [a d]
  [:abbrev {:title d} a])

(defn blockquote [data & source]
  (if-let [src (first source)]
    [:blockquote.blockquote data
     [:footer.blockquote-footer src]]
    [:blockquote.blockquote data]))

(defn plain-list [items]
  (into
   [:ul.list-unstyled]
   (for [i items]
     ^{:key (gu/defkey i)} [:li i])))

(defn inline-list [items]
  (into
   [:ul.list-inline]
   (for [i items]
     ^{:key (gu/defkey i)} [:li.list-inline-item i])))

(defn inline-code [data]
  [:code data])

(defn code-block [lines]
  (into
   [:pre]
   (for [l lines]
     [:code (str l "\n")])))

(defn variable [data]
  [:var data])

(defn user-input [data]
  [:kbd data])

(defn console-output [data]
  [:samp data])


