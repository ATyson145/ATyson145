fun movePawn(chessboard: Chessboard, fromX: Int, fromY: Int, toX: Int, toY: Int, color: Color): Boolean {
    if (chessboard.isPawnMoveValid(fromX, fromY, toX, toY, color)) {
        val piece = chessboard.board[fromY][fromX]!!
        chessboard.board[fromY][fromX] = null
        chessboard.board[toY][toX] = piece
        return true
    }
    return false
}

fun moveRook(chessboard: Chessboard, fromX: Int, fromY: Int, toX: Int, toY: Int, color: Color): Boolean {
    if (chessboard.isRookMoveValid(fromX, fromY, toX, toY, color, chessboard)) {
        val piece = chessboard.board[fromY][fromX]!!
        chessboard.board[fromY][fromX] = null
        chessboard.board[toY][toX] = piece
        return true
    }
    return false
}

fun moveBishop(chessboard: Chessboard, fromX: Int, fromY: Int, toX: Int, toY: Int, color: Color): Boolean {
    if (chessboard.isBishopMoveValid(fromX, fromY, toX, toY, color)) {
        val piece = chessboard.board[fromY][fromX]!!
        chessboard.board[fromY][fromX] = null
        chessboard.board[toY][toX] = piece
        return true
    }
    return false
}

fun moveKnight(chessboard: Chessboard, fromX: Int, fromY: Int, toX: Int, toY: Int, color: Color): Boolean {
    if (chessboard.isKnightMoveValid(fromX, fromY, toX, toY, color)) {
        val piece = chessboard.board[fromY][fromX]!!
        chessboard.board[fromY][fromX] = null
        chessboard.board[toY][toX] = piece
        return true
    }
    return false
}

fun moveQueen(chessboard: Chessboard, fromX: Int, fromY: Int, toX: Int, toY: Int, color: Color): Boolean {
    if (chessboard.isQueenMoveValid(fromX, fromY, toX, toY, color, chessboard)) {
        val piece = chessboard.board[fromY][fromX]!!
        chessboard.board[fromY][fromX] = null
        chessboard.board[toY][toX] = piece
        return true
    }
    return false
}

fun moveKing(chessboard: Chessboard, fromX: Int, fromY: Int, toX: Int, toY: Int, color: Color): Boolean {
    if (chessboard.isKingMoveValid(fromX, fromY, toX, toY, color)) {
        val piece = chessboard.board[fromY][fromX]!!
        chessboard.board[fromY][fromX] = null
        chessboard.board[toY][toX] = piece
        return true
    }
    return false
}

fun main() {
    val chessboard = Chessboard()
    chessboard.printBoard()

    var whiteTurn = true

    while (true) {
        val currentPlayer = if (whiteTurn) Color.WHITE else Color.BLACK
        val opponentColor = if (currentPlayer == Color.WHITE) Color.BLACK else Color.WHITE
        val playerLabel = if (whiteTurn) "White" else "Black"

        println("$playerLabel's turn")
        println("Enter the position of the piece you want to move (e.g., e2): ")
        val fromInput = readlnOrNull()
        val from = parsePosition(fromInput)

        if (from != null) {
            val (fromX, fromY) = from

            println("Enter the destination position (e.g., e4): ")
            val toInput = readlnOrNull()
            val to = parsePosition(toInput)

            if (to != null) {
                val (toX, toY) = to

                val piece = chessboard.board[fromY][fromX]
                if (piece != null && piece.color == currentPlayer) {
                    val moveResult = when (piece.type) {
                        PieceType.PAWN -> movePawn(chessboard, fromX, fromY, toX, toY, currentPlayer)
                        PieceType.ROOK -> moveRook(chessboard, fromX, fromY, toX, toY, currentPlayer)
                        PieceType.BISHOP -> moveBishop(chessboard, fromX, fromY, toX, toY, currentPlayer)
                        PieceType.KNIGHT -> moveKnight(chessboard, fromX, fromY, toX, toY, currentPlayer)
                        PieceType.QUEEN -> moveQueen(chessboard, fromX, fromY, toX, toY, currentPlayer)
                        PieceType.KING -> moveKing(chessboard, fromX, fromY, toX, toY, currentPlayer)
                    }
                    if (moveResult) {
                        chessboard.printBoard()
                        whiteTurn = !whiteTurn
                    }
                    else {
                        println("Invalid move. Try again")
                    }
                } else {
                    println("Invalid piece. Try again.")
                }
            } else {
                println("Invalid destination position.")
            }
        } else {
            println("Invalid position.")
        }
    }
}

fun parsePosition(input: String?): Pair<Int, Int>? {
    if (input == null || input.length != 2) return null

    val x = input[0] - 'a'
    val y = 8 - (input[1] - '0')

    if(x < 0 || x>= 8 || y < 0 || y >= 8) return null

    return Pair(x, y)
}