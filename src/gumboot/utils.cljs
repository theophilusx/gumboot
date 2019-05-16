(ns gumboot.utils
  (:require [clojure.string :as string]))

(defn defkey [s]
  (str (string/replace s #"\s" "-") "-key"))

(defn str->keyword [s]
  (keyword (string/replace (string/lower-case s) #"\s+" "-")))

(defn keyword->title [k]
  (string/join " "
   (map #'string/capitalize
        (string/split
         (string/replace (name k) #"-" " ")
         " "))))
