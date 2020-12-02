module Part2 where

import Control.Monad (join)

main :: IO ()
main = join $ print . (\xs -> [x * y * z | x <- xs, y <- xs, z <- xs, x + y + z == 2020]). (fmap read) . lines <$> getContents
