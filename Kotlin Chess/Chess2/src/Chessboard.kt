import java.util.*
import kotlin.math.abs

class Chessboard {
    val board: Array<Array<Piece?>> = Array(8) { arrayOfNulls<Piece?>(8) }

    init {
        setupBoard()
    }

    private fun setupBoard() {
        for (i in 0..7) {
            // Pawns
            board[1][i] = Piece(PieceType.PAWN, Color.BLACK)
            board[6][i] = Piece(PieceType.PAWN, Color.WHITE)
        }

        // Rooks
        board[0][0] = Piece(PieceType.ROOK, Color.BLACK)
        board[0][7] = Piece(PieceType.ROOK, Color.BLACK)
        board[7][0] = Piece(PieceType.ROOK, Color.WHITE)
        board[7][7] = Piece(PieceType.ROOK, Color.WHITE)

        // Knights
        board[0][1] = Piece(PieceType.KNIGHT, Color.BLACK)
        board[0][6] = Piece(PieceType.KNIGHT, Color.BLACK)
        board[7][1] = Piece(PieceType.KNIGHT, Color.WHITE)
        board[7][6] = Piece(PieceType.KNIGHT, Color.WHITE)

        // Bishops
        board[0][2] = Piece(PieceType.BISHOP, Color.BLACK)
        board[0][5] = Piece(PieceType.BISHOP, Color.BLACK)
        board[7][2] = Piece(PieceType.BISHOP, Color.WHITE)
        board[7][5] = Piece(PieceType.BISHOP, Color.WHITE)

        // Queens
        board[0][3] = Piece(PieceType.QUEEN, Color.BLACK)
        board[7][3] = Piece(PieceType.QUEEN, Color.WHITE)

        // Kings
        board[0][4] = Piece(PieceType.KING, Color.BLACK)
        board[7][4] = Piece(PieceType.KING, Color.WHITE)
    }

    fun printBoard() {
        println("  Black's Side  ")
        println("  +------------------------+")
        println("  | a  b  c  d  e  f  g  h |")
        println("+-+----------------------+-+")
        for (i in 0..7) {
            print("${8 - i}| ") // Print the row number
            for (j in 0..7) {
                val piece = board[i][j]
                val symbol = when (piece?.type) {
                    PieceType.KING -> "K"
                    PieceType.QUEEN -> "Q"
                    PieceType.ROOK -> "R"
                    PieceType.BISHOP -> "B"
                    PieceType.KNIGHT -> "N"
                    PieceType.PAWN -> "P"
                    else -> "."
                }
                val displaySymbol = if (piece?.color == Color.WHITE) symbol else symbol.lowercase(Locale.getDefault())
                if ((i + j) % 2 == 0) {
                    // White square
                    print("\u001B[47m $displaySymbol \u001B[0m")
                } else {
                    // Black square
                    print("\u001B[40m $displaySymbol \u001B[0m")
                }
            }
            println("|${8 - i}") // Print the row number
        }
        println("+-+----------------------+-+")
        println("  | a  b  c  d  e  f  g  h |")
        println("  +------------------------+")
        println("  White's Side  ")
    }

    fun isPawnMoveValid(fromX: Int, fromY: Int, toX: Int, toY: Int, color: Color): Boolean {
        val target = board[toY][toX]

        val forwardDirection = if (color == Color.WHITE) -1 else 1
        val startRow = if (color == Color.WHITE) 6 else 1

        if (toX == fromX && toY == fromY + forwardDirection && target == null) {
            return true
        }

        if (toX == fromX && fromY == startRow && toY == fromY + 2 * forwardDirection && target == null && board[fromY + forwardDirection][fromX] == null) {
            return true
        }

        if (abs(toX - fromX) == 1 && toY == fromY + forwardDirection && target != null && target.color != color) {
            return true
        }
        return false
    }

    fun isRookMoveValid(fromX: Int, fromY: Int, toX: Int, toY: Int, color: Color, chessboard: Chessboard): Boolean {
        if (fromX != toX && fromY != toY) {
            return false
        }

        val deltaX = if (toX > fromX) 1 else if (toX < fromX) -1 else 0
        val deltaY = if (toY > fromY) 1 else if (toY < fromY) -1 else 0

        var x = fromX + deltaX
        var y = fromY + deltaY
        while (x != toX || y != toY) {
            if (chessboard.board[y][x] != null) {
                return false
            }
            x += deltaX
            y += deltaY
        }

        val destinationPiece = chessboard.board[toY][toX]
        return destinationPiece == null || destinationPiece.color != color
    }

    fun isBishopMoveValid(fromX: Int, fromY: Int, toX: Int, toY: Int, color: Color): Boolean {
        if (abs(toX - fromX) != abs(toY - fromY)) {
            return false
        }

        val deltaX = if (toX > fromX) 1 else -1
        val deltaY = if (toY > fromY) 1 else -1

        var x = fromX + deltaX
        var y = fromY + deltaY
        while (x != toX && y != toY) {
            if (board[y][x] != null) {
                return false
            }
            x += deltaX
            y += deltaY
        }
        val destinationPiece = board[toY][toX]
        return destinationPiece == null || destinationPiece.color != color
    }

    fun isKnightMoveValid(fromX: Int, fromY: Int, toX: Int, toY: Int, color: Color): Boolean {
        val dx = abs(toX - fromX)
        val dy = abs(toY - fromY)

        if ((dx == 2 && dy == 1) || (dx == 1 && dy == 2)) {
            val destinationPiece = board[toY][toX]
            return destinationPiece == null || destinationPiece.color != color
        }
        return false
    }

    fun isQueenMoveValid(fromX: Int, fromY: Int, toX: Int, toY: Int, color: Color, chessboard: Chessboard): Boolean {
        return isRookMoveValid(fromX, fromY, toX, toY, color, chessboard) || isBishopMoveValid(
            fromX,
            fromY,
            toX,
            toY,
            color
        )
    }

    fun isKingMoveValid(fromX: Int, fromY: Int, toX: Int, toY: Int, color: Color): Boolean {
        if (abs(toX - fromX) <= 1 && abs(toY - fromY) <= 1) {
            val destinationPiece = board[toY][toX]
            return destinationPiece == null || destinationPiece.color != color
        }
        return false
    }
}