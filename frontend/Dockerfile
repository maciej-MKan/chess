FROM node:18-alpine

WORKDIR /app

ARG VITE_BACKEND_URI

ENV VITE_BACKEND_URI=$VITE_BACKEND_URI

COPY package.json .

RUN npm install -g npm@10.5.1
RUN npm install -g typescript
RUN npm install

COPY . .

RUN npm run build

EXPOSE 5173

CMD [ "npm", "run", "serve" ]
