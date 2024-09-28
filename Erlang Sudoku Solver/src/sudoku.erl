-module(sudoku).
-export([solve/1, print_board/1, generate_empty_board/0, generate_partial_board/1, insert_value/4, parse_board/1]).

% Main function to solve the Sudoku puzzle
solve(Board) when is_list(Board) ->
    case find_empty(Board) of
        false -> 
            io:format("Solved Board:~n"),
            print_board(Board),
            Board; % Return the solved board
        {Row, Col} -> 
            try_numbers(Board, Row, Col) % Try numbers for the empty cell
    end;
solve(BoardString) when is_binary(BoardString) ->
    Board = parse_board(BoardString),
    solve(Board). % Parse the board string and solve

% Try numbers 1 to 9 for the given cell
try_numbers(Board, Row, Col) ->
    try_numbers(Board, Row, Col, lists:seq(1, 9)).

% If no numbers left to try, return false
try_numbers(_Board, _Row, _Col, []) ->
    false;
try_numbers(Board, Row, Col, [N | Rest]) ->
    if_valid_try_numbers(Board, Row, Col, N, Rest).

% If the number is valid, set it and try to solve the board
if_valid_try_numbers(Board, Row, Col, N, Rest) ->
    case is_valid(Board, Row, Col, N) of
        true ->
            NewBoard = set_number(Board, Row, Col, N),
            SolvedBoard = solve(NewBoard),
            case SolvedBoard of
                false -> try_numbers(Board, Row, Col, Rest); % Try next number if not solved
                _ -> SolvedBoard % Return solved board
            end;
        false ->
            try_numbers(Board, Row, Col, Rest) % Try next number
    end.

% Check if placing Num at (Row, Col) is valid
is_valid(Board, Row, Col, Num) when Num > 0, Num =< 9 ->
    RowList = lists:nth(Row, Board),
    ColList = [lists:nth(Col, R) || R <- Board],
    Subgrid = get_subgrid(Board, Row, Col),
    RowValid = not lists:member(Num, RowList),
    ColValid = not lists:member(Num, ColList),
    SubgridValid = not lists:member(Num, Subgrid),
    RowValid andalso ColValid andalso SubgridValid.

% Get the 3x3 subgrid for the given cell
get_subgrid(Board, Row, Col) ->
    StartRow = 3 * ((Row - 1) div 3) + 1,
    StartCol = 3 * ((Col - 1) div 3) + 1,
    [lists:nth(StartCol + C, lists:nth(StartRow + R, Board))
     || R <- [0, 1, 2], C <- [0, 1, 2]].

% Find the next empty cell
find_empty(Board) ->
    find_empty(Board, 1).

% Recursive helper to find empty cell
find_empty([], _) -> false;
find_empty([Row | Rest], RowIndex) ->
    case find_empty_in_row(Row, 1) of
        false -> find_empty(Rest, RowIndex + 1);
        ColIndex -> {RowIndex, ColIndex}
    end.

% Find empty cell in a row
find_empty_in_row([], _) -> false;
find_empty_in_row([0 | _Rest], ColIndex) -> ColIndex;
find_empty_in_row([_ | Rest], ColIndex) -> find_empty_in_row(Rest, ColIndex + 1).

% Set a number in the board
set_number(Board, Row, Col, Num) ->
    RowList = lists:nth(Row, Board),
    NewRowList = substitute_at(Col, Num, RowList),
    substitute_at(Row, NewRowList, Board).

% Substitute a value at the given index in a list
substitute_at(Index, Value, List) ->
    {Left, [_ | Right]} = lists:split(Index - 1, List),
    lists:append(Left, [Value | Right]).

% Print the board to the console
print_board(Board) ->
    lists:map(fun(Row) -> io:format("~p~n", [Row]) end, Board).

% Generate an empty 9x9 board
generate_empty_board() ->
    lists:duplicate(9, lists:duplicate(9, 0)).

% Generate a partially filled board with N random numbers
generate_partial_board(N) ->
    Board = generate_empty_board(),
    populate_board(Board, N).

% Populate the board with N random numbers
populate_board(Board, 0) -> Board;
populate_board(Board, N) ->
    Row = rand:uniform(9),
    Col = rand:uniform(9),
    Num = rand:uniform(9),
    case is_valid(Board, Row, Col, Num) of
        true ->
            NewBoard = set_number(Board, Row, Col, Num),
            populate_board(NewBoard, N - 1);
        false ->
            populate_board(Board, N)
    end.

% Insert a value into the board
insert_value(Board, Row, Col, Value) ->
    set_number(Board, Row, Col, Value).

% Parse a board from a binary string
parse_board(BoardString) ->
    Rows = [binary:part(BoardString, {N, 9}) || N <- lists:seq(0, 72, 9)],
    [parse_row(Row) || Row <- Rows].

% Parse a row from a binary string
parse_row(RowString) ->
    [list_to_integer([C]) || C <- binary:bin_to_list(RowString)].
