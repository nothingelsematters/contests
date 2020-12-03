module Day1 where

import Control.Monad (join)

main :: IO ()
main = join $ print . (\xs -> (head [x * y | x <- xs, y <- xs], head [x * y | x <- xs, y <- xs, x + y == 2020])). (fmap read) . lines <$> getContents
