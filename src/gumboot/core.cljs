(ns ^:figwheel-hooks gumboot.core
  (:require [reagent.core :as r]
            [gumboot.typography :as typ]
            [gumboot.table :as tbl]))

;; define your app data so that it doesn't get over-written on reload
(defonce app-state (r/atom {:text "Hello world!"}))

(defonce sample-headers ["Heading 1" "Heading 2" "Heading 3"])

(defonce sample-rows [["Cell 1a" "Cell 1b" "Cell 1c"]
                      ["Cell 2a" "Cell 2b" "Cell 2c"]
                      ["Cell 3a" "Cell 3b" "Cell 3c"]
                      ["Cell 4a" "Cell 4b" "Cell 4c"]])

(defn multiply [a b]
  (* a b))

;; specify reload hook with ^;after-load metadata
(defn ^:after-load on-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  (swap! app-state update-in [:__figwheel_counter] inc))

(defn typography-component []
  [:div
   [typ/display 4 "Sample of various typography components"]
   [:p [typ/highlight "This text should be highlighted in some way"]]
   [:p [typ/deleted "This text should look like it has been deleted"]]
   [:p [typ/inserted "This text should look like added or inserted text"]]
   [:p [typ/small "This text should look small"]]
   [:p [typ/strong "This text should be strong"]]
   [:p [typ/underline "This text should be underlined"]]
   [:p [typ/emphasize "This should be emphasized"]]
   [:p "This is an abbreviation definitions "
    [typ/abbrev "FIGJAM" "Fuck I'm Good, Just Ask Me"]]
   [:p [typ/blockquote "This is a block quote with no citations"]]
   [:p [typ/blockquote "This is a block quote with a citations" "by me"]]
   [:p "A plan list" [typ/plain-list ["Item 1" "Item 2" "Item 3" "Item 4"]]]
   [:p "This is an inline list " [typ/inline-list ["Item A" "Item B" "Item C"]]]
   [:p "This is some " [typ/inline-code " inline code"] " style"]
   [typ/code-block ["This is some"
                    "example code block"
                    "lines"]]
   [:p "This is a " [typ/variable "variable"] " style"]
   [:p "This is some " [typ/user-input "example user input"]]
   [:p "And here is some " [typ/console-output "example output from a console"]]])

(defn table-component []
  [:div
   [typ/display 4 "Table Components"]
   [tbl/table sample-headers sample-rows]
   [:p "Dark Table"]
   [tbl/table sample-headers sample-rows :table-class "table table-dark"]
   [:p "A sample vertical table"]
   [tbl/v-table sample-rows]])


(defn home []
  [:div.container
   [typ/display 1 "The Demo Page"]
   [:p (:text @app-state)]
   [typography-component]
   [table-component]
   [:hr]
   [:p "Figwheel reload counter: " (:__figwheel_counter @app-state)]])

(r/render [home]
          (js/document.getElementById "app"))
