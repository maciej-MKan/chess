import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react-swc'

// https://vitejs.dev/config/
export default defineConfig({
  base: '/',
  plugins: [react()],
  preview: {
    port: 5173,
    strictPort: true,
  },
  // test: {
  //   globals: true,
  //   environment: 'jsdom',
  //   setupFiles: './src/setupTests.ts',
  //   css: true,
  //   reporters: ['verbose'],
  //   coverage: {
  //     reporter: ['text', 'json', 'html'],
  //     include: ['src/**/*'],
  //     exclude: [],
  //   }
  // },
})
