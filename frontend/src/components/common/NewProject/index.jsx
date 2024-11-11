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
  border-radius: 10px;
  padding: 12px 20px;
  font-size: 20px;
  font-family: 'PaperlogyBold';
  cursor: pointer;
  width: 23vw;
  max-width: 300px;
  min-width: 150px;

  @media (max-width: 768px) {
    font-size: 13px;
    padding: 10px 14px;
  }

  @media (max-width: 480px) {
    font-size: 12px;
    padding: 8px 12px;
  }
`;

const Icon = styled(FaFolderPlus)`
  margin-right: 8px;
  font-size: 20px;

  @media (max-width: 768px) {
    font-size: 13px;
  }

  @media (max-width: 480px) {
    font-size: 12px;
  }
`;
