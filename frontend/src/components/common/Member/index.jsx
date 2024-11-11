import React from 'react';
import PropTypes from 'prop-types';
import styled from 'styled-components';
import { FaCrown } from 'react-icons/fa';

const Member = ({ members }) => (
  <Container>
    <Header>
      <Title>멤버 관리하기</Title>
      <InviteButton>초대하기</InviteButton>
    </Header>
    <MemberList>
      {members.map((member) => (
        <MemberItem key={member.id}>
          <MemberName>
            {member.name}
            {member.isAdmin && <CrownIcon />}
          </MemberName>
          {!member.isAdmin && <KickButton>추방</KickButton>}
        </MemberItem>
      ))}
    </MemberList>
  </Container>
);

Member.propTypes = {
  members: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.number.isRequired,
      name: PropTypes.string.isRequired,
      isAdmin: PropTypes.bool.isRequired,
    }),
  ).isRequired,
};

export default Member;

const Container = styled.div`
  padding: 20px;
`;

const Header = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 15px;
`;

const Title = styled.h3`
  font-size: 20px;
  font-weight: bold;
  color: #333;
`;

const InviteButton = styled.button`
  background-color: #ffd771;
  color: #fff;
  border: none;
  border-radius: 5px;
  padding: 5px 10px;
  font-size: 14px;
  cursor: pointer;

  &:hover {
    background-color: #ffd966;
  }
`;

const MemberList = styled.ul`
  list-style-type: none;
  padding: 0;
  margin: 0;
`;

const MemberItem = styled.li`
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 10px;
`;

const MemberName = styled.span`
  font-size: 16px;
  color: #333;
  display: flex;
  align-items: center;
  position: relative;
  padding-left: 12px;

  &::before {
    content: '';
    display: inline-block;
    width: 6px;
    height: 6px;
    background-color: #333;
    border-radius: 50%;
    position: absolute;
    left: 0;
    top: 50%;
    transform: translateY(-50%);
  }
`;

const CrownIcon = styled(FaCrown)`
  color: #ffd700;
  margin-left: 5px;
  font-size: 16px;
`;

const KickButton = styled.button`
  background-color: #ffb3b3;
  color: white;
  border: none;
  border-radius: 10px;
  padding: 5px 10px;
  font-size: 14px;
  cursor: pointer;

  &:hover {
    background-color: #ff8080;
  }
`;
