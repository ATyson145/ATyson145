%%%-------------------------------------------------------------------
%% @doc sprint5 public API
%% @end
%%%-------------------------------------------------------------------

-module(sprint5_app).

-behaviour(application).

-export([start/2, stop/1]).

start(_StartType, _StartArgs) ->
    sprint5_sup:start_link().

stop(_State) ->
    ok.

%% internal functions
