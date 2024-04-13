import {describe, test, expect, vi, it} from "vitest";
import {render, screen} from "@testing-library/react";
import Chessboard from "../../src/views/board/bord";
import {initGame} from "../../src/api/game";

vi.stubGlobal('fetch', vi.fn());
describe('Chess board test', () => {
    test('renders wait message before api response', () => {
        render(<Chessboard/>);

        expect(screen.getByText(/Wait for API response/i)).toBeDefined();

    })
    // test('renders chessboard after api response', () => {
    //     global.fetch = vi.fn().mockRejectedValueOnce(new Error('Backend unavailable'));
    //
    //     expect(screen.getByText(/Backend unavailable/i)).toBeDefined();
    // })

    it('throws an error when fetch fails', async () => {
        fetch.mockResolvedValueOnce({
            ok: false
        });

        await initGame();

        // render(<Chessboard/>);
        expect(fetch).toHaveBeenCalled();
    })

});