module Part1 where

import Control.Monad (join)

main :: IO ()
main = join $ print . (\xs -> [x * y | x <- xs, y <- xs, x + y == 2020]). (fmap read) . lines <$> getContents
