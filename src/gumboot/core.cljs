(ns ^:figwheel-hooks gumboot.core
  (:require [reagent.core :as r]
            [gumboot.typography :as typ]
            [gumboot.table :as tbl]
            [gumboot.alert :as alt]
            [gumboot.badge :as bdg]
            [gumboot.card :as crd]))

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
   [typ/display 4 "Typography"]
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
   [:p "A plan list"
    [typ/plain-list ["Item 1" "Item 2" "Item 3" "Item 4"]]]
   [:p "This is an inline list "
    [typ/inline-list ["Item A" "Item B" "Item C"]]]
   [:p "This is some " [typ/inline-code " inline code"] " style"]
   [typ/code-block ["This is some"
                    "example code block"
                    "lines"]]
   [:p "This is a " [typ/variable "variable"] " style"]
   [:p "This is some " [typ/user-input "example user input"]]
   [:p "And here is some "
    [typ/console-output "example output from a console"]]])

(defn table-component []
  [:div
   [typ/display 4 "Tables"]
   [tbl/table sample-headers sample-rows]
   [:p "Dark Table"]
   [tbl/table sample-headers sample-rows :table-class "table table-dark"]
   [:p "A sample vertical table"]
   [tbl/v-table sample-rows]])

(defn alert-component []
  [:div
   [typ/display 4 "Alerts"]
   [alt/alert-primary "A primaary alert"]
   [alt/alert-secondary "A secondary alert"]
   [alt/alert-success "A success alert"]
   [alt/alert-warning "A warning alert"]
   [alt/alert-danger "A danger alert"]
   [alt/alert-info "An info alert"]
   [alt/alert-light "A light alert"]
   [alt/alert-dark "A dark alert"]
   [alt/alert-primary-d "A primaary dismissible alert"]
   [alt/alert-secondary-d "A secondary dismissible alert"]
   [alt/alert-success-d "A success dismissible alert"]
   [alt/alert-warning-d  "A warning dismissible alert"]
   [alt/alert-danger-d "A danger dismissible alert"]
   [alt/alert-info-d "An info dismissible alert"]
   [alt/alert-light-d "A light dismissible alert"]
   [alt/alert-dark-d "A dark dismissible alert"]])

(defn badge-component []
  [:div
   [typ/display 4 "Badges"]
   [:p "Primary badge " [bdg/badge-primary 1]]
   [:p "Secondary badge " [bdg/badge-secondary 2]]
   [:p "Success badge " [bdg/badge-success 3]]
   [:p "Warning badge " [bdg/badge-warning 4]]
   [:p "Danger badge " [bdg/badge-danger 5]]
   [:p "Info badge " [bdg/badge-info 6]]
   [:p "Light badge " [bdg/badge-light 7]]
   [:p "Dark badge " [bdg/badge-dark 8]]
   [typ/display 5 "Pill Badges"]
   [:p "Primary pill badge " [bdg/badge-primary-p "1p"]]
   [:p "Secondary pill badge " [bdg/badge-secondary-p "2p"]]
   [:p "Success pill badge " [bdg/badge-success-p "3p"]]
   [:p "Warning pill badge " [bdg/badge-warning-p "4p"]]
   [:p "Danger pill badge " [bdg/badge-danger-p "5p"]]
   [:p "Info pill badge " [bdg/badge-info-p "6p"]]
   [:p "Light pill badge " [bdg/badge-light-p "7p"]]
   [:p "Dark pill badge " [bdg/badge-dark-p "8p"]]])

(defn card-component []
  [:div
   [typ/display 4 "Cards"]
   [crd/card "A very basic card"]
   [crd/card "A card with a title" :title "Some Title"]
   [crd/card
    "A card with a header & title"
    :title "Title" :header "the header"]
   [crd/card
    "A card with a header, footer & title"
    :title "A title" :header "The header" :footer "The footer"]
   [crd/card
    "A card with colour"
    :card-class "bg-primary"]
   [crd/card
    "A card with some colurs"
    :header "The header" :footer "The footer" :title "A Title"
    :card-class "text-info" :header-class "text-warning"
    :footer-class "text-danger"]
   [:p "Now some collapsible cards"]
   [crd/card-collapsable
    "card1id"
    "card 1 header"
    "This is card one!"]
   [crd/card-collapsable
    "card2id"
    "card 2 header"
    "A collapsible card with a title"
    :title "A card title"]
   [crd/card-collapsable
    "card3id" "Card 3 Header"
    "A collapsable card with title and footer"
    :title "The Title"
    :footer "The footer"]])

(defn home []
  [:div.container
   [typ/display 1 "The Demo Page"]
   [:p (:text @app-state)]
   [typography-component]
   [table-component]
   [alert-component]
   [badge-component]
   [card-component]
   [:hr]
   [:p "Figwheel reload counter: " (:__figwheel_counter @app-state)]])

(r/render [home]
          (js/document.getElementById "app"))
