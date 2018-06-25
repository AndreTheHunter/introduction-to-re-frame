(ns andrethehunter.introduction-to-re-frame.view.highlight
  (:require
    ["highlight.js/lib/highlight" :as hljs]
    ["highlight.js/lib/languages/clojure" :as clj]
    ["highlight.js/lib/languages/javascript" :as js]
    [clojure.string :as string]
    [oops.core :refer [ocall]]
    [reagent.core :as r]))

(def ^:private init
  (delay (doto hljs
           (ocall "registerLanguage" "clojure" clj)
           (ocall "registerLanguage" "javascript" js))))

(defn- highlight-block [node]
  @init
  (let [dom-node (r/dom-node node)]
    (ocall hljs "highlightBlock"
      (ocall dom-node "querySelector" "code"))))

(defn- highlight [language code]
  (r/create-class
    {:display-name         "hljs-code"
     :component-did-mount  highlight-block
     :component-did-update highlight-block
     :reagent-render       (fn [language code]
                             [:pre [:code {:class language} (string/trim code)]])}))

(defn clojure [s]
  [highlight "clojure" s])

(defn javascript [s]
  [highlight "javascript" s])
