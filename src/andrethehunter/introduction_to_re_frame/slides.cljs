(ns ^:dev/always andrethehunter.introduction-to-re-frame.slides
  (:require
    [andrethehunter.introduction-to-re-frame.io :refer [slurp]]
    [andrethehunter.introduction-to-re-frame.graph :refer [graph-svg]]
    [oops.core :refer [oget]]
    [reagent.core :as r]
    [todo.events :as todo-event]
    [todo.views :as todo-view]
    [re-frame.core :as rf]))

(def title
  {:title ["Introduction to re-frame"
           ;TODO re-frame logo
           "by André de Jager"]})
(def overview
  {:heading "Overview"
   :points  ["What is re-frame"
             "Example application"
             "Why re-frame?"
             "Regularly pause for questions"]})
(def about-me
  {:heading "About Me"
   :points  [["Lead software developer at " [:link ["JESI" "https://jesi.io/"]]]
             "Building web-apps for the past 7 years"
             "Been using re-frame for 1.5 years"
             "Not the author of re-frame"]})
(def what-is-re-frame
  {:heading "What is re-frame"
   :points  ["A front-end framework"
             "Written in Clojure"
             "Built on top of Reagent"
             "Built on top of React"
             "Functional Reactive Programming style"
             [:link "https://github.com/Day8/re-frame"]]})
(def functional-programming
  {:heading "Functional Programming?"
   :points  ["Programming with functions, functions as values"
             "State transformed through a series of functions"
             "Opposite of Object Oriented Programming"
             [:link ["What is Functional Programming and Why Should I Care?"
                     "https://www.youtube.com/watch?v=fCasWdNwdD0"]]]})
(def clojure
  {:heading "Clojure?"
   :points  ["A functional LISP"
             "Immutability is built-in"
             "Compiles to Java, JavaScript, .NET, & more"
             ["Read " [:link ["Clojure for the Brave and True"
                              "https://www.braveclojure.com/clojure-for-the-brave-and-true/"]]]
             [:link "https://clojure.org/"]]})
(def clojure-example
  {:heading "Introduction to Clojure"
   :points  [["JavaScript (not Clojure)" [:code/js (slurp "resources/clojure.js")]]
             ["Clojure" [:code/clj (slurp "resources/clojure.clj")]]
             "Congratulations, you now know Clojure!"]})
(def clojure-example-more
  {:heading  "More Clojure"
   :code/clj (slurp "resources/more-clojure.clj")})
(def reagent
  {:heading "Reagent?"
   :points  ["Simple ClojureScript interface for React"
             "Create React components simply using functions"
             ["Returns a simpler representation of HTML, "
              [:link ["Hiccup"
                      "https://github.com/weavejester/hiccup"]]
              [:code/clj (slurp "resources/hiccup.clj")]]
             [:link "https://reagent-project.github.io/"]]})
(def reagent-example
  {:heading  "Reagent Example"
   :code/clj (slurp "resources/reagent-example.clj")
   ;TODO insert code from file
   :live     (fn hello []
               (r/with-let [value (r/atom "World")]
                 [:div
                  [:p (str "Hello, " @value "!")]
                  [:input {:value     @value
                           :on-change #(reset! value
                                         (oget % ["target" "value"]))}]]))})

(def frp
  {:heading "Functional Reactive Programming?"
   ;https://netopyr.com/2016/10/11/mvc-dead-comes-next/
   :points  ["Uses Functional Programming principles"
             "Changes flow in a single direction"
             "Continuous render loop"
             "Opposite of Model View Controller pattern"
             ["In many other front-end frameworks e.g. " [:link ["Redux.js" "https://redux.js.org/"]]]]})
(def re-frame-flow
  {:heading "re-frame flow"
   ;TODO better layout (different graphing library maybe?)
   :svg     (graph-svg {:db           [:subscription]
                        :subscription [:view]
                        :view         [:DOM :event]
                        :DOM          [:view]
                        :event        [:db]})})
(def example
  {:heading ["Example TODO app"]
   :live    (do
              (rf/dispatch [::todo-event/initialise])
              todo-view/main)})
(def example-event
  {:heading  "Events - update the app DB"
   :code/clj (slurp "src/todo/events.cljs")})
(def example-sub
  {:heading  "Subscriptions - query view data from the DB"
   :code/clj (slurp "src/todo/subs.cljs")})
(def example-view
  {:heading  "Views - display data and trigger events"
   :code/clj (slurp "src/todo/views.cljs")})
(def comparison
  {:heading "Speed"
   :points  ["Even faster than Redux"
             [:link ["A Real-World Comparison of Front-End Frameworks with Benchmarks"
                     "https:// medium.freecodecamp.org/a-real-world-comparison-of-front-end-frameworks-with-benchmarks-e1cb62fd526c"]]]})
;https://medium.freecodecamp.org/a-real-world-comparison-of-front-end-frameworks-with-benchmarks-2018-update-e5760fb4a962
(def tooling
  ;live re-load
  {:heading "Tooling"
   :points  ["Compiles to optimized JavaScript"]})
(def why?
  {:heading "Why re-frame?"})
;but why?.gif
(def questions
  {:title ["Questions?"]})
(def thanks
  {:title ["Thank You!"]})
(def -order
  [
   title
   overview
   about-me
   what-is-re-frame
   functional-programming
   clojure
   clojure-example
   clojure-example-more
   reagent
   reagent-example
   frp
   re-frame-flow
   example
   example-event
   example-sub
   example-view
   questions
   thanks])
