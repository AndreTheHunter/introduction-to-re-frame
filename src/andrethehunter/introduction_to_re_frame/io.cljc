(ns andrethehunter.introduction-to-re-frame.io
  (:refer-clojure :exclude [slurp])
  #?(:cljs (:require-macros andrethehunter.introduction-to-re-frame.io)))

(defmacro slurp [file]
  (clojure.core/slurp file))
