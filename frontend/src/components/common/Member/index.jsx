import React, { useState } from 'react';
import PropTypes from 'prop-types';
import styled from 'styled-components';
// eslint-disable-next-line import/no-unresolved
import MinModal from '@components/common/MinModal';

const Member = ({ members }) => {
  const [isModalOpen, setIsModalOpen] = useState(false);

  const openModal = () => {
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
  };

  return (
    <Container>
      <Header>
        <Title>멤버 관리하기</Title>
        <InviteButton onClick={openModal}>초대하기</InviteButton>
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

      {isModalOpen && (
        <MinModal
          title="초대하기"
          description="초대하실 분의 이메일을 입력해 주세요."
          onCancel={closeModal}
          onConfirm={closeModal}
        />
      )}
    </Container>
  );
};

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
  padding: 0 1vw 1vh 1vw;
`;

const Header = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 1.5vh;
`;

const Title = styled.h3`
  font-size: 2vh;
  font-weight: bold;
  color: #333;
`;

const InviteButton = styled.button`
  background-color: #ffd771;
  color: #fff;
  border: none;
  border-radius: 0.5vw;
  padding: 0.5vh 1vw;
  font-size: 1.4vh;
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
  margin-bottom: 1vh;
`;

const MemberName = styled.span`
  font-size: 1.6vh;
  color: #333;
  display: flex;
  align-items: center;
  position: relative;
  padding-left: 1.2vw;

  &::before {
    content: '';
    display: inline-block;
    width: 0.6vw;
    height: 0.6vw;
    background-color: #333;
    border-radius: 50%;
    position: absolute;
    left: 0;
    top: 50%;
    transform: translateY(-50%);
  }
`;

const CrownIcon = styled.span`
  color: #ffd700;
  margin-left: 0.5vw;
  font-size: 1.6vh;
`;

const KickButton = styled.button`
  background-color: #ffb3b3;
  color: white;
  border: none;
  border-radius: 1vw;
  padding: 0.5vh 1vw;
  font-size: 1.4vh;
  cursor: pointer;

  &:hover {
    background-color: #ff8080;
  }
`;
