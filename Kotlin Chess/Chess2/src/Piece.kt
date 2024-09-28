enum class PieceType {
    KING, QUEEN, ROOK, BISHOP, KNIGHT, PAWN
}

enum class Color {
    WHITE, BLACK
}

data class Piece(val type: PieceType, val color: Color)