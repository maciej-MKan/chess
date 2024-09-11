import {describe, expect, test, vi} from "vitest";
import {render, screen} from "@testing-library/react";
import GameScreen from "../../src/views/board/GameScreen";

vi.stubGlobal('fetch', vi.fn());
describe('Chess board test', () => {
    test('renders wait message before api response', () => {
        render(<GameScreen/>);

        expect(screen.getByText(/Wait for API response/i)).toBeDefined();

    })
    // it('renders chessboard after api response', async () => {
    //     global.fetch = vi.fn().mockResolvedValueOnce({
    //                 ok: false,
    //                 body: () => {
    //                     return Promise.resolve(JSON.stringify(null));
    //                 },
    //             });
    //
    //     render(<Chessboard/>)
    //
    //     expect(await screen.getByText(/Backend unavailable/i)).toBeDefined();
    // })

    // it('throws an error when fetch fails', async () => {
    //     fetch.mockResolvedValueOnce({
    //         ok: false,
    //         body: () => {
    //             return Promise.resolve(JSON.stringify(null));
    //         },
    //     });
    //
    //     await initGame();
    //
    //     // render(<Chessboard/>);
    //     expect(fetch).toHaveBeenCalled();
    // })

});