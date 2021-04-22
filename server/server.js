const express = require('express');
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

app.get('/', (req, res) => {
  res.send('Running');
});

const botName = 'admin';

io.on('connection', (socket) => {
  // 화상 채팅
  socket.on('joinVideoRoom', (_id, { userId, roomId }) => {
    const user = userJoin(_id, userId, roomId);

    socket.join(user.roomId);

    socket.emit(
      'videoMessage',
      formatMessage(botName, `${user.userId}님 환영합니다!`)
    );

    socket.broadcast.to(roomId).emit('user-connected', _id);

    socket.broadcast
      .to(user.roomId)
      .emit(
        'videoMessage',
        formatMessage(botName, `${user.userId}님이 참석하셨습니다.`)
      );

    socket.on('videoChatMessage', (userId, msg) => {
      const user = getCurrentUser(userId);

      io.to(user.roomId).emit('videoMessage', formatMessage(user.userId, msg));
    });

    socket.on('disconnect', () => {
      const user = getCurrentUser(_id);

      if (user) {
        socket.broadcast.to(roomId).emit('user-disconnected', _id);

        io.to(user.roomId).emit(
          'videoMessage',
          formatMessage(botName, `${user.userId} 님이 나가셨습니다.`)
        );
      }
    });
  });

  // 채팅
  socket.on('joinChatRoom', ({ userId, roomId }) => {
    const user = userJoin(socket.id, userId, roomId);

    socket.join(user.roomId);
  });

  socket.on('chatMessage', (msg, time) => {
    const user = getCurrentUser(socket.id);

    const message = { userId: user.userId, text: msg, time };

    io.to(user.roomId).emit('message', message);
  });

  socket.on('disconnect', () => {
    //const user = userLeave(socket.id);
    // if (user) {
    //   io.to(user.roomId).emit(
    //     'message',
    //     formatMessage(botName, `${user.userId}님이 채팅방을 나갔습니다.`)
    //   );
    // }
  });
});

const port = process.env.PORT || 5000;

server.listen(port, () => {
  console.log(`Server is running on ${port}`);
});
