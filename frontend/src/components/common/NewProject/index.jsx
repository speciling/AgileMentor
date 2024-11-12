import React, { useState } from 'react';
import styled from 'styled-components';
import { FaFolderPlus } from 'react-icons/fa';
// eslint-disable-next-line import/no-unresolved
import MinModal from '@components/common/MinModal';

const CreateProjectButton = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);

  const openModal = () => {
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
  };

  return (
    <>
      <Button onClick={openModal}>
        <Icon />
        프로젝트 만들기
      </Button>

      {isModalOpen && (
        <MinModal
          title="프로젝트 생성"
          description="프로젝트 이름"
          onCancel={closeModal}
          onConfirm={closeModal}
        />
      )}
    </>
  );
};

export default CreateProjectButton;

const Button = styled.button`
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #b0c9f8;
  color: #fff;
  border: none;
  border-radius: 1vh;
  padding: 1.2vh 2vw;
  font-size: 1.8vh;
  font-family: 'PaperlogyBold';
  cursor: pointer;
  width: 15vw;

  @media (max-width: 768px) {
    font-size: 1.2vh;
    padding: 1vh 1.4vw;
  }

  @media (max-width: 480px) {
    font-size: 1vh;
    padding: 0.8vh 1.2vw;
  }
`;

const Icon = styled(FaFolderPlus)`
  margin-right: 0.8vw;
  font-size: 2vh;

  @media (max-width: 768px) {
    font-size: 1.3vh;
  }

  @media (max-width: 480px) {
    font-size: 1.2vh;
  }
`;
