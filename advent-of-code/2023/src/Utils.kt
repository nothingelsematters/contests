enum class Direction(val index2: Index2) {
    Up(Index2(-1, 0)),
    Down(Index2(1, 0)),
    Left(Index2(0, -1)),
    Right(Index2(0, 1));

    fun reversed() = when (this) {
        Up -> Down
        Down -> Up
        Left -> Right
        Right -> Left
    }
}
