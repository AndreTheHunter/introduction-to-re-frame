(defproject com.github.andrethehunter/introduction-to-re-frame "1.0.0"
  :dependencies [[thheller/shadow-cljs "2.4.5"]
                 [re-frame "0.10.5"]
                 ;keyboard
                 [re-pressed "0.2.1"]
                 ;JS interop
                 [binaryage/oops "0.6.2"]
                 ;live recompiling (for examples)
                 [org.clojure/tools.reader "1.2.1"]
                 ;graphs
                 [rhizome "0.2.9"]]
  :profiles {:dev {:dependencies [[binaryage/devtools "0.9.10"]]}})
