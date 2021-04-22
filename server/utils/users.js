const users = [];

const userJoin = (_id, userId, roomId) => {
  const user = { _id, userId, roomId };

  users.push(user);

  return user;
};

const getCurrentUser = (id) => {
  return users.find((user) => user._id === id);
};

module.exports = {
  userJoin,
  getCurrentUser,
};
