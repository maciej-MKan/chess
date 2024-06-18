FROM node:18

WORKDIR /app

COPY package.json .

RUN npm install -g npm@10.5.1
RUN npm install -g typescript
RUN npm install

COPY . .

EXPOSE 5173

RUN ["chmod", "+x", "./env.sh"]

ENTRYPOINT ["/bin/sh",  "-c", "./env.sh"]
