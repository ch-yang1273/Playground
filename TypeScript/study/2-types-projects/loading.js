{
    var printLoginState = function (state) {
        switch (state.state) {
            case "loading":
                console.log('👀 loading...');
                break;
            case "success":
                console.log("\uD83D\uDE03 ".concat(state.response.body));
                break;
            case "fail":
                console.log('😱 no network');
                break;
            default:
                Error('Unknown State');
        }
    };
    printLoginState({ state: 'loading' }); // 👀 loading...
    printLoginState({ state: 'success', response: { body: 'loaded' } }); // 😃 loaded
    printLoginState({ state: 'fail', reason: 'no network' }); // 😱 no network
}
