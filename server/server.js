const express = require('express');
//const { v4: uuidV4 } = require('uuid');
const cors = require('cors');
const { userJoin, getCurrentUser, userLeave } = require('./utils/users');
const formatMessage = require('./utils/messages');

const app = express();
const server = require('http').Server(app);
const io = require('socket.io')(server, {
  cors: {
    origin: '*',
    methods: ['GET', 'POST'],
  },
});

const { ExpressPeerServer } = require('peer');
const peerServer = ExpressPeerServer(server, {
  debug: true,
});

//app.use('/peerjs', peerServer);

app.use(cors());

const botName = 'admin';

io.on('connection', (socket) => {
  socket.on('join-room', (userId, { username, roomId }) => {
    const user = userJoin(userId, username, roomId);

    //console.log(username, ' : ', userId);

    socket.join(user.roomId);

    socket.emit('message', formatMessage(botName, '환영합니다!'));

    socket.broadcast.to(roomId).emit('user-connected', userId);

    socket.broadcast
      .to(user.roomId)
      .emit(
        'message',
        formatMessage(botName, `${user.username} 님이 참석하셨습니다.`)
      );

    socket.on('chatMessage', (userId, msg) => {
      const user = getCurrentUser(userId);

      io.to(user.roomId).emit('message', formatMessage(user.username, msg));
    });

    socket.on('disconnect', () => {
      const user = getCurrentUser(userId);
      if (user) {
        socket.broadcast.to(roomId).emit('user-disconnected', user.userId);

        io.to(user.roomId).emit(
          'message',
          formatMessage(botName, `${user.username} 님이 나가셨습니다.`)
        );
      }
    });
  });
});

const port = process.env.PORT || 5000;

server.listen(port, () => {
  console.log(`Server is running on ${port}`);
});
