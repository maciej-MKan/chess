const {writeFileSync} = require('fs');
const {resolve} = require('path');

const targetPath = resolve(__dirname, './.env');
const envConfigFile = `VITE_BACKEND_URI=${process.env.API_URL}`;

writeFileSync(targetPath, envConfigFile, 'utf8');