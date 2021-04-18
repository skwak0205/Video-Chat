const users = [];

const userJoin = (userId, username, roomId) => {
  const user = { userId, username, roomId };

  users.push(user);

  return user;
};

const getCurrentUser = (id) => {
  return users.find((user) => user.userId === id);
};

module.exports = {
  userJoin,
  getCurrentUser,
};
