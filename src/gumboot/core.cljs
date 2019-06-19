(ns ^:figwheel-hooks gumboot.core
  (:require [reagent.core :as r]
            [gumboot.typography :as typ]
            [gumboot.table :as tbl]
            [gumboot.alert :as alt]
            [gumboot.badge :as bdg]
            [gumboot.card :as crd]
            [gumboot.input :as ipt]
            [gumboot.data-grid :as grid]))

;; define your app data so that it doesn't get over-written on reload
(defonce app-state (r/atom {:text "Hello world!"}))

(defonce sample-headers ["Heading 1" "Heading 2" "Heading 3"])

(defonce sample-rows [["Cell 1a" "Cell 1b" "Cell 1c"]
                      ["Cell 2a" "Cell 2b" "Cell 2c"]
                      ["Cell 3a" "Cell 3b" "Cell 3c"]
                      ["Cell 4a" "Cell 4b" "Cell 4c"]])

(defonce sample-rows2 [{:a 1 :b "one" :c "Just one"}
                       {:a 2 :b "two" :c "just two"}
                       {:a 3 :b "three" :c "just three"}])

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
   [:p]
   [typ/highlight "This text should be highlighted in some way"]
   [:p]
   [typ/deleted "This text should look like it has been deleted"]
   [:p]
   [typ/inserted "This text should look like added or inserted text"]
   [:p]
   [typ/small "This text should look small"]
   [:p]
   [typ/strong "This text should be strong"]
   [:p]
   [typ/underline "This text should be underlined"]
   [:p]
   [typ/emphasize "This should be emphasized"]
   [:p]
   "This is an abbreviation definitions "
   [typ/abbrev "FIGJAM" "Fuck I'm Good, Just Ask Me"]
   [:p]
   [typ/blockquote "This is a block quote with no citations"]
   [:p]
   [typ/blockquote "This is a block quote with a citations" "by me"]
   [:p]
   "A plan list"
   [typ/plain-list ["Item 1" "Item 2" "Item 3" "Item 4"]]
   [:p]
   "This is an inline list "
   [typ/inline-list ["Item A" "Item B" "Item C"]]
   [:p]
   [typ/code-block ["This is some"
                    "example code block"
                    "lines"]]
   "This is some " [typ/inline-code " inline code"] " style"
   [:p]
   "This is a " [typ/variable "variable"] " style"
   [:p]
   "This is some " [typ/user-input "example user input"]
   [:p]
   "And here is some "
   [typ/console-output "example output from a console"]])

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
   [:p]
   "Primary badge " [bdg/badge-primary 1]
   [:p]
   "Secondary badge " [bdg/badge-secondary 2]
   [:p]
   "Success badge " [bdg/badge-success 3]
   [:p]
   "Warning badge " [bdg/badge-warning 4]
   [:p]
   "Danger badge " [bdg/badge-danger 5]
   [:p]
   "Info badge " [bdg/badge-info 6]
   [:p]
   "Light badge " [bdg/badge-light 7]
   [:p]
    "Dark badge " [bdg/badge-dark 8]
   [typ/display 5 "Pill Badges"]
   [:p]
    "Primary pill badge " [bdg/badge-primary-p "1p"]
   [:p]
    "Secondary pill badge " [bdg/badge-secondary-p "2p"]
   [:p]
    "Success pill badge " [bdg/badge-success-p "3p"]
   [:p]
    "Warning pill badge " [bdg/badge-warning-p "4p"]
   [:p]
    "Danger pill badge " [bdg/badge-danger-p "5p"]
   [:p]
    "Info pill badge " [bdg/badge-info-p "6p"]
   [:p]
    "Light pill badge " [bdg/badge-light-p "7p"]
   [:p]
    "Dark pill badge " [bdg/badge-dark-p "8p"]])

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

(defn input-component []
  (let [data (r/atom {})]
    (fn []
      [:div
       [typ/display 4 "Input Fields"]
       [:div.row
        [:div.col
         [ipt/text :test-text data]]
        [:div.col
         "Some test text input "
         [typ/inline-code (get @data :test-text)]]]
       [:div.row
        [:div.col
         [ipt/text :default-test-text data :attrs {:defaultValue "A default value"}]]
        [:div.col
         "Some default test text input "
         [typ/inline-code (get @data :default-test-text)]]]

       [:div.row
        [:div.col
         [ipt/text :inline-test-text data :inline true]]
        [:div.col
         "Inline test test input "
         [typ/inline-code (get @data :inline-test-text)]]]
       [:div.row
        [:div.col
         [ipt/password :test-password data]]
        [:div.col
         "A test password "
         [typ/inline-code (get @data :test-password)]]]
       [:div.row
        [:div.col
         [ipt/password :inline-test-password data :inline true]]
        [:div.col
         "Inline password test "
         [typ/inline-code (get @data :inline-test-password)]]]
       [:div.row
        [:div.col
         [ipt/tel :test-phone data]]
        [:div.col
         "A test phone number "
         [typ/inline-code (get @data :test-phone)]]]
       [:div.row
        [:div.col
         [ipt/tel :inline-test-phone data :inline true]]
        [:div.col
         "An inline phone number test "
         [typ/inline-code (get @data :inline-test-phone)]]]
       [:div.row
        [:div.col
         [ipt/email :test-email data]]
        [:div.col
         "A test email field "
         [typ/inline-code (get @data :test-email)]]]
       [:div.row
        [:div.col
         [ipt/email :inline-test-email data :inline true]]
        [:div.col
         "An inline email test "
         [typ/inline-code (get @data :inline-test-email)]]]
       [:div.row
        [:div.col
         [ipt/number :test-number data]]
        [:div.col
         "A test number field "
         [typ/inline-code (get @data :test-number)]]]
       [:div.row
        [:div.col
         [ipt/number :inline-test-number data :inline true]]
        [:div.col
         "An inline number test "
         [typ/inline-code (get @data :inline-test-number)]]]
       [:div.row
        [:div.col
         [ipt/number-range :test-range data 1 100]]
        [:div.col
         "A test range "
         [typ/inline-code (get @data :test-range)]]]
       [:div.row
        [:div.col
         [ipt/number-range :inline-test-range data 1 100 :inline true]]
        [:div.col
         "Inline range test "
         [typ/inline-code (get @data :inline-test-range)]]]
       [:div.row
        [:div.col
         [ipt/date :test-date data]]
        [:div.col
         "A test date input field "
         [typ/inline-code (get @data :test-date)]]]
       [:div.row
        [:div.col
         [ipt/date :inline-test-date data :inline true]]
        [:div.col
         "Inline date test "
         [typ/inline-code (get @data :inline-test-date)]]]
       [:div.row
        [:div.col
         [ipt/time-input :test-time data]]
        [:div.col
         "A test time field "
         [typ/inline-code (get @data :test-time)]]]
       [:div.row
        [:div.col
         [ipt/time-input :inline-test-time data :inline true]]
        [:div.col
         "Inline test time field "
         [typ/inline-code (get @data :inline-test-time)]]]
       [:div.row
        [:div.col
         [ipt/select :select-test
          [[:error "Error"] [:warning "Warning"] [:info "Info"]] data :default :info]]
        [:div.col
         "A test select "
         [typ/inline-code (str (get @data :select-test))]]]
       [:div.row
        [:div.col
         [ipt/select :inline-select-test ["Tom" "Dick" "Harry"] data :inline true]]
        [:div.col
         "An inline select test "
         [typ/inline-code (get @data :inline-select-test)]]]
       [:div.row
        [:div.col
         [ipt/list-input :test-list ["choice 1" "choice 2" "choice 3"] data]]
        [:div.col
         "A test list input "
         [typ/inline-code (get @data :test-list)]]]
       [:div.row
        [:div.col
         [ipt/list-input :inline-list-test ["choice 1" "choice 2" "choice 3"] data :inline true]]
        [:div.col
         "inline list test "
         [typ/inline-code (get @data :inline-list-test)]]]
       [:div.row
        [:div.col
         [ipt/yes-or-no :y-or-n-test data]]
        [:div.col
         "A test of yes-or-no "
         [typ/inline-code (str (get @data :y-or-n-test))]]]
       [:div.row
        [:div.col
         [ipt/yes-or-no :inline-y-or-n-test data :inline true]]
        [:div.col
         "Inline yes or no test "
         [typ/inline-code (str (get @data :inline-y-or-n-test))]]]
       [:hr]
       [:p (str @data)]])
    )
  )

(defn grid-component []
  [:div
   [typ/display 4 "Data Grids"]
   [grid/data-grid sample-headers sample-rows2]
   
   ])

(defn home []
    [:div.container
     [typ/display 1 "The Demo Page"]
     [:p]
     (:text @app-state)
     [typography-component]
     [table-component]
     [alert-component]
     [badge-component]
     [card-component]
     [input-component]
     [grid-component]
     [:hr]
     [:p]
     "Figwheel reload counter: " (:__figwheel_counter @app-state)])

(r/render [home]
          (js/document.getElementById "app"))
